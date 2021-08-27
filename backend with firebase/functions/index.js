const functions = require("firebase-functions");
const express = require("express");
const bodyParser = require("body-parser");
const fs = require("fs");
const cors = require("cors");

const app = express();
app.use(cors({ origin: true }));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

app.get("/api/leaderbord", (req, res, next) => {
  const { currentUserId } = req.query;
  if (!currentUserId) {
    throw new Error("missing current userid");
  }
  fs.readFile("./data/leaderboard.json", "utf-8", async (err, response) => {
    if (err) {
      console.log(err);
      throw new Error("something went wrong, please try again");
    }
    try {
      const data = await JSON.parse(response);
      const users = Object.entries(data);
      const filteredUser = users.filter((user) => user[0] == currentUserId);
      if (filteredUser.length === 0) {
        res.status(200);
        res.json({
          message:
            "Current user id does not exist! Please specify an existing user id!",
          displayedUsers: [],
        });
        return;
      } else {
        //arrange users in descending order based on thier number of bananas
        const usersInDescOrder = [];
        const usersProp = users.map((user) => user[1]);
        for (i = 0; i < usersProp.length; i++) {
          let biggestBananasUser = {};
          biggestBananasUser.bananas = 0;
          let biggestUserIndex;
          for (j = 0; j < usersProp.length; j++) {
            if (usersProp[j].bananas >= biggestBananasUser.bananas) {
              biggestBananasUser = usersProp[j];
              biggestUserIndex = j;
            }
          }
          usersProp.splice(biggestUserIndex, 1);
          usersInDescOrder.push(biggestBananasUser);
        }

        const descUsersAsEntries = Object.entries(usersInDescOrder);

        //specifiying the current user
        let currentUser;
        descUsersAsEntries.map((user) => {
          if (user[1] === filteredUser[0][1]) {
            currentUser = user;
          }
        });

        //prepare the 10 users to be displayed
        let theDisplayedUsers = [];
        descUsersAsEntries.map((user) => {
          if (currentUser[0] > 10) {
            //if current user is not within the first 10
            if (user[1] === filteredUser[0][1] || user[0] < 9) {
              if (user[1] === filteredUser[0][1]) {
                user[1].isCurrentUser = true;
              } else {
                user[1].isCurrentUser = false;
              }
              user[0] = parseInt(user[0]) + 1;
              theDisplayedUsers.push(user);
            }
          } else {
            //if current user is within the first 10
            if (user[1] === filteredUser[0][1]) {
              user[1].isCurrentUser = true;
            } else {
              user[1].isCurrentUser = false;
            }
            if (user[1] === filteredUser[0][1] || user[0] < 10) {
              user[0] = parseInt(user[0]) + 1;
              theDisplayedUsers.push(user);
            }
          }
        });
        // const lastDisplayedUsers = theDisplayedUsers.map((user) => ({
        //   [user[0]]: user[1],
        // }));

        const lastDisplayedUsers = theDisplayedUsers.map((user) => {
          user[1].rank = user[0];
          return user[1];
        });

        res.json({ message: "succeded", displayedUsers: lastDisplayedUsers });
      }
    } catch (error) {
      throw new Error("something went wrong, please try again");
    }
  });
});

app.use((req, res, next) => {
  throw new Error("couldn't find this route");
});

app.use((error, req, res, next) => {
  if (req.headerSent) {
    return next(error);
  }
  res.status(error.code || 500);
  res.json({ message: "An unknown error occured" });
});

// app.listen(process.env.PORT || 5001, () => {
//   console.log("Serr server is running on port 5000");
// });

exports.expressApi = functions.https.onRequest(app);
