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


var database;


http.createServer(app).listen(app.get('port'), function(){
  console.log('Express server listening on port ' + app.get('port'));
  MongoClient.connect(myurl, function(err, db){
      console.log("Connected sucessfully to server");
      database = db;
  });
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
    var current_collection = dbo.collection("Current_user");


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
                    current_collection.insertOne(user, function(err, res) {
                        console.log("added user to current_user!");
                    })
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

app.use('/summoner', function(req, res, next) {

    console.log('다섯 번째 미들웨어 호출 됨');
    

    var paramSummoner = req.body.summoner;
    var paramTier = req.body.tier;

    var dbo = database.db("mydb");
    var champ_collection = dbo.collection("Champions");
    var current_collection = dbo.collection("Current_user");
    var Name;
    var mySummoner;
    var approve ={'approve':'NO'};

    current_collection.findOne({}, function (err, curr) {
        //console.log(curr.name);
        try {
            Name = curr.name;

            

            current_collection.updateOne(curr, {$set: {summoner: paramSummoner, tier: paramTier}},function (err, res) {

                try {
                    console.log("updated my current_collection");
                    console.log(paramTier);
                } catch (err) {
                    console.log("failed");
                }

                mySummoner = { name: Name, summoner: paramSummoner, tier: paramTier};
              
                champ_collection.find({"summoner": paramSummoner}).count(function(err, number){

                    if (number != 0) {
                        console.log("summoner already exists in database");
                        res.send(approve);
                    } else {
                        champ_collection.insertOne(mySummoner, function(err, result) {
                            console.log("added my summoner to database!");
                            approve ={'approve':'OK', user: mySummoner};
                            res.send(approve);
                        });
                    }

                });
                
            });
        } catch  (err) {
            console.log("failed");
            res.send(approve);
        }
        
    });

    

    // var approve ={'approve':'OK'};

    res.send(approve);

});

app.use('/bringsummoner', function(req, res, next) {

    console.log('여섯 번째 미들웨어 호출 ');
    

    var paramSummoner = req.body.summoner;

    var dbo = database.db("mydb");
    var champ_collection = dbo.collection("Champions");
    var current_collection = dbo.collection("Current_user");
    current_collection.findOne({}, function(err, curr) {
        champ_collection.find({}).toArray(function(err, result) {

            res.send({user: curr, friends: result});
        });
        
    });

});

app.use('/bringfriends', function(req, res, next) {

    console.log('friendssss ');
    

    //var paramSummoner = req.body.summoner;

    var dbo = database.db("mydb");
    var champ_collection = dbo.collection("Champions");
    //var current_collection = dbo.collection("Current_user");

    console.log(champ_collection);
    res.send(champ_collection);
    // current_collection.findOne({}, function (err, curr) {
    //     console.log(curr.name);
    //     console.log(curr.summoner);
    //     //JSON.stringify({ a: 1 })
    //     //res.send(JSON.stringify({"mine": curr, "friends": champ_collection}));
    //     //res.send({mine: curr, friends: champ_collection});
    //     res.send(curr);
        
    //     // var Name = curr.name;
    // });



});

app.use('/checksummoner', function(req, res, next) {

    console.log('일곱 번째 미들웨어 호출 됨 - name');
    

    //var paramSummoner = req.body.summoner;

    var dbo = database.db("mydb");
    var champ_collection = dbo.collection("Champions");
    var current_collection = dbo.collection("Current_user");
    var approve ={'approve':'OK'};
    var Summoner;

    current_collection.findOne({}, function (err, curr) {
        //console.log(curr.name);
        try {
            Summoner = curr.summoner;
            console.log(Summoner);
            if (Summoner == undefined) {
                var approve ={'approve':'OK'};
                approve.approve = 'NO';
                res.send(approve);
            } else {
                var approve ={'approve':'OK', user: curr};
                res.send(approve);
            }
            

        } catch  (err) {
            approve.approve = 'NO';
            res.send(approve);
            console.log("failed");
        }
        
    });
    

});

app.use('/addfriend', function(req, res, next) {

    console.log('여덟 번째 미들웨어 호출');
    

    var paramName = req.body.name;
    var paramSummoner = req.body.summoner;
    var paramTier = req.body.tier;
    

    //var paramSummoner = req.body.summoner;

    var dbo = database.db("mydb");
    var champ_collection = dbo.collection("Champions");
    //var current_collection = dbo.collection("Current_user");
    var approve ={'approve':'OK'};


    champ_collection.insertOne({name: paramName, summoner: paramSummoner, tier: paramTier}, function (err, curr) {
        //console.log(curr.name);
        try {

            res.send(approve);

            

        } catch  (err) {
            approve.approve = 'NO';
            res.send(approve);
            console.log("failed");
        }
        
    });


});