var MongoClient = require('mongodb').MongoClient;
var crypto = require('crypto');
var express = require('express');
var bodyParser = require('body-parser');
var http = require('http');

var myurl = "mongodb+srv://codnjs:cuty6193A@B!@cluster0-hqqa9.mongodb.net/test";
//var myurl = "mongodb://localhost:27017/mydb";

var app = express();
app.set('port',process.env.PORT || 3000);
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}));


// MongoClient.connect(myurl, {useNewUrlParser: true},function(err, db) {
//   if (err) throw err;
//   var dbo = db.db("mydb");

//   //app.post('/login', (request, response, next) => {
//     // var post_data = request.body;
//     // var password = post_data.password;
//     // var email = post_data.email;

//     dbo.collection("user_collection").find({"email": "hi@naver.com"}).count(function(err, number) {
//         if (err) throw err;
        
//          if (number == 0) {
//              console.log("Email does not exist");
//          } else {
//         //     dbo.collection('user_collection')
//         //                 .findOne({'email': "hi@naver.com"}, function(err, user){
//         //                     var saved_password = user.password;
//         //                     if (saved_password == "1234") {
//         //                         response.json('Login success!');
//         //                         console.log('Login success!');
//         //                     } else {
//         //                         response.json('Wrong password!');
//         //                         console.log('Wrong password!');
//         //                     }
//         //                 })
//              console.log("Email does exist");
//          }
        
//         db.close();
//       });                                                                                                                    

//   //})

  
//   app.listen(3000);
//   console.log('Example app listening on port 3000!');
//   db.close();
// });

var database;



http.createServer(app).listen(app.get('port'), function(){
  console.log('Express server listening on port ' + app.get('port'));
  MongoClient.connect(myurl, function(err, db){
      console.log("Connected sucessfully to server");
      database = db;
  });
});

// app.get('/user/info', function(req, res){
//     var dbo = database.db("mydb");
//     var collection = dbo.collection("user_collection");
//     collection.find({}).toArray(function(err, docs){
//         //assert.equal(err, null);
//         console.log("Found the following records");
//         res.json(docs);
//     });
// });


app.use('/login', function(req, res, next) {

    console.log('첫 번째 미들웨어 호출 됨');
    


    var paramId = req.body.id;
    var paramPassword = req.body.password;
    console.log('id : '+paramId+'  pw : '+paramPassword);

    //아이디 일치여부 flag json 데이터입니다.
    // if(paramId == 'test01') approve.approve_id = 'OK';
    // if(paramPassword == '123') approve.approve_pw = 'OK';

    var dbo = database.db("mydb");
    var collection = dbo.collection("user_collection");
    collection.find({"email": String(paramId)}).count(function(err, number){
        //assert.equal(err, null);
        var approve ={'approve_id':'NO','approve_pw':'NO'};
        console.log("Found the following records");
        if (number == 0) {
            console.log("Email does not exist");
        } else {
        collection
            .findOne({'email': paramId}, function(err, user){
                var saved_password = user.password;
                if (saved_password == paramPassword) {
                    //response.json('Login success!');
                    approve.approve_pw = 'OK';
                    approve.approve_id = 'OK';
                    console.log('Login success!');
                    res.send(approve);
                } else {
                    //response.json('Wrong password!');
                    
                    console.log('Wrong password!');
                    res.send(approve);
                }
            })
            //console.log("Email does exist");
        }
        //res.json(docs);
        //res.send(approve);
    });

    //res.send(approve);

});

app.use('/signup', function(req, res, next) {

    console.log('두 번째 미들웨어 호출 됨');
    

    var paramName = req.body.name;
    var paramId = req.body.id;
    var paramPassword = req.body.password;
    console.log('name: ' + paramName + 'id : '+paramId+'  pw : '+paramPassword);

    //아이디 일치여부 flag json 데이터입니다.
    // if(paramId == 'test01') approve.approve_id = 'OK';
    // if(paramPassword == '123') approve.approve_pw = 'OK';

    var dbo = database.db("mydb");
    var collection = dbo.collection("user_collection");
    collection.find({"email": paramId}).count(function(err, number){
        //assert.equal(err, null);
        var approve ={'approve_id':'NO','approve_pw':'NO'};
        //console.log("Found the following records");
        if (number != 0) {
            console.log("number" + number);
            console.log("Email already exists!");
            res.send(approve);
        } else {
            var newUser = { name: paramName, email: paramId, password: paramPassword };
            collection
                .insertOne(newUser, function(err, res) {
                    
                //db.close();
            });
            approve.approve_pw = 'OK';
                    approve.approve_id = 'OK';
                    res.send(approve);
                //if (err) throw err;
                console.log("added new user");
        }
        //res.json(docs);
        //res.send(approve);
    });

    //res.send(approve);

});

app.use('/login', function(req, res, next) {

    console.log('첫 번째 미들웨어 호출 됨');
    


    var paramId = req.body.id;
    var paramPassword = req.body.password;
    console.log('id : '+paramId+'  pw : '+paramPassword);

    //아이디 일치여부 flag json 데이터입니다.
    // if(paramId == 'test01') approve.approve_id = 'OK';
    // if(paramPassword == '123') approve.approve_pw = 'OK';

    var dbo = database.db("mydb");
    var collection = dbo.collection("user_collection");
    collection.find({"email": String(paramId)}).count(function(err, number){
        //assert.equal(err, null);
        var approve ={'approve_id':'NO','approve_pw':'NO'};
        console.log("Found the following records");
        if (number == 0) {
            console.log("Email does not exist");
        } else {
        collection
            .findOne({'email': paramId}, function(err, user){
                var saved_password = user.password;
                if (saved_password == paramPassword) {
                    //response.json('Login success!');
                    approve.approve_pw = 'OK';
                    approve.approve_id = 'OK';
                    console.log('Login success!');
                    res.send(approve);
                } else {
                    //response.json('Wrong password!');
                    
                    console.log('Wrong password!');
                    res.send(approve);
                }
            })
            //console.log("Email does exist");
        }
        //res.json(docs);
        //res.send(approve);
    });

    //res.send(approve);

});

app.use('/contacts', function(req, res, next) {

    console.log('세 번째 미들웨어 호출 됨');
    

    var paramName = req.body.name;
    var paramNumber = req.body.number;
    //console.log('name: ' + paramName + 'id : '+paramId+'  pw : '+paramPassword);

    //아이디 일치여부 flag json 데이터입니다.
    // if(paramId == 'test01') approve.approve_id = 'OK';
    // if(paramPassword == '123') approve.approve_pw = 'OK';

    var dbo = database.db("mydb");
    var collection = dbo.collection("Contacts");

    collection.find({"number": paramNumber}).count(function(err, number){
        //assert.equal(err, null);
        var approve ={'approve':'NO'};
        //console.log("Found the following records");
        if (number != 0) {
            console.log("number" + paramNumber);
            console.log("Number already exists!");
            res.send(approve);
        } else {
            var newContact = { name: paramName, number: paramNumber};
            collection
                .insertOne(newContact, function(err, res) {
                    
                //db.close();
            });
            approve.approve = 'OK';
            res.send(approve);
                //if (err) throw err;
            console.log("added contact");
        }
        //res.json(docs);
        //res.send(approve);
    });

    //res.send(approve);

});

app.use('/gallery', function(req, res, next) {

    console.log('네 번째 미들웨어 호출 됨');
    

    var paramUri = req.body.uri;
    //var paramNumber = req.body.number;
    //console.log('name: ' + paramName + 'id : '+paramId+'  pw : '+paramPassword);

    //아이디 일치여부 flag json 데이터입니다.
    // if(paramId == 'test01') approve.approve_id = 'OK';
    // if(paramPassword == '123') approve.approve_pw = 'OK';

    var dbo = database.db("mydb");
    
    var collection = dbo.collection("Gallery");

    collection.find({"uri": paramUri}).count(function(err, number){
        //assert.equal(err, null);
        var approve ={'approve':'NO'};
        //console.log("Found the following records");
        if (number != 0) {
            //console.log("number" + paramNumber);
            console.log("photo already exists in database");
            //approve.approve = 'OK';
            res.send(approve);
        } else {
            var newUri = { uri: paramUri};
            collection
                .insertOne(newUri, function(err, res) {
                    
                //db.close();
            });
            approve.approve = 'OK';
            res.send(approve);
                //if (err) throw err;
            console.log("added gallery uri");
        }
        //res.json(docs);
        //res.send(approve);
    });

    //res.send(approve);

});

// app.post('/login', (request, response, next) => {
//     var post_data = request.body;
//     var password = post_data.password;
//     var email = post_data.email;

//     dbo.collection("user_collection").find({"email": "hi@naver.com"}).count(function(err, number) {
//         if (err) throw err;
        
//          if (number == 0) {
//              console.log("Email does not exist");
//          } else {
//             dbo.collection('user_collection')
//                         .findOne({'email': "hi@naver.com"}, function(err, user){
//                             var saved_password = user.password;
//                             if (saved_password == "1234") {
//                                 response.json('Login success!');
//                                 console.log('Login success!');
//                             } else {
//                                 response.json('Wrong password!');
//                                 console.log('Wrong password!');
//                             }
//                         })
//              console.log("Email does exist");
//          }
        
//         db.close();
//       });                                                                                                                    

//   });