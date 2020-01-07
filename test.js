var MongoClient = require('mongodb').MongoClient;
//var ObjectID = mongodb.ObjectID;
var crypto = require('crypto');
var express = require('express');
var bodyParser = require('body-parser');



var url = "mongodb://localhost:27017/mydb";
var myurl = "mongodb+srv://codnjs:cuty6193A@B!@cluster0-hqqa9.mongodb.net/test";

MongoClient.connect(myurl, {useNewUrlParser: true},function(err, db) {
  if (err) throw err;
  var dbo = db.db("mydb");

  dbo.collection("user_collection").insert({"email": "hi@naver.com", "password": "1234"}, function(err, res) {
    if (err) throw err;
    console.log("Collection created!");
    db.close();
  });

  console.log("Added user");
  db.close();
});

app.listen(3000, () => {

  console.log('Example app listening on port 3000!');

});