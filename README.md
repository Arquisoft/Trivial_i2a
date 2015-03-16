Trivial i2a
===============

[![Build
Status](https://travis-ci.org/Arquisoft/Trivial_i2a.svg?branch=master)](https://travis-ci.org/Arquisoft/Trivial_i2a)

Repository for Trivial programs. 

Software Architecture course - [University of Oviedo](http://www.uniovi.es), 2014-15

# About the parser

##Why Scala

We implemented the parser in Scala because its functional programming features make it a very suitable language to build a parser. Parser combinators have been of a great help; once you learn to use them, the increase in productivity is huge. 

Parser combinators is a feature borrowed from Haskell, a pure functional language you should probably take a look at.

## Supported formats

* GIFT 
* QTI

##Supported types of questions

* Single choice questions
* Multiple choice questions (with weighted answers)
* True/False questions


#User manual

##Preparation

Click [here](https://github.com/Arquisoft/Trivial_i2a/releases/download/1.0/trivialparser-1.0.jar) to download the application.

You will need Java installed in your machine in order to execute the application. To check if you have Java installed in your computer, type:

```
$ java -version
```

The result should be something like this:

```
java version "1.8.0_20"
Java(TM) SE Runtime Environment (build 1.8.0_20-b26)
Java HotSpot(TM) 64-Bit Server VM (build 25.20-b23, mixed mode)
```

## Usage

Let's see the help contents of the application to see all the available options:

```
$ java -jar trivialparser-1.0.jar --help
trivialparser 1.x
Usage: trivialparser [insert] [options]

  -i <filename> | --input <filename>
        input is the questions filename
  -o <filename> | --output <filename>
        filename is the output file where the questions should be extracted to
  -v | --verbose
        print output stream
  --help
        prints the help
Command: insert [options]
Insert to database
  -d <db> | --dbname <db>
        db is the name of the output database
  -c <collection> | --collection <collection>
        collection is the output collection
```

##A basic example

Let's try the application. Create a file singlechoicequestion.gift with the following content:

```
//comment
$CATEGORY:category
::title::Who is buried in Grant's tomb in New York City?{
=Grant
#Yesss
~Ruben
#Noooooo
}
```

This is a single choice question written in GIFT format. Let's ask our parser to read this file and translate it to JSON:

```
$ java -jar trivialparser-1.0.jar --input singlechoicequestion.gift --verbose
```
And the output will be question translated to JSON:
```json
{
  "category" : "category",
  "title" : "title",
  "wording" : "Who is buried in Grant's tomb in New York City?",
  "options" : [ {
    "wording" : "Grant",
    "comment" : "Yesss",
    "correct" : true
  }, {
    "wording" : "Ruben",
    "comment" : "Noooooo",
    "correct" : false
  } ]
}
```
Note that the --verbose (or -v) option is necessary in order to see the result. If we want to save the result to a file using the --output option:

```
$ java -jar trivialparser-1.0.jar --input singlechoicequestion.gift --output result.json
```


## GIFT

The GIFT format is explained here: https://docs.moodle.org/23/en/GIFT_format


###Note about writing GIFT files

Specifying the category is not mandatory. Comments and question titles are optional too.


###Single choice questions

If you want to try the single choice questions support, create a file named "test.gift" with the following questions in GIFT format

```
//comment
$CATEGORY:category
::title::Who is buried in Grant's tomb in New York City?{
    =Grant
    #Yesss
  ~Ruben
  #Noooooo
}

::title::Who is buried in Grant's tomb in New York City?{
  =Grant
  #Yesss
  ~Ruben
  #Noooooo
}

Who is buried in Grant's tomb in New York City?{
  =Grant
  #Yesss
  ~Ruben
  #Noooooo
}

$CATEGORY:category
Who is buried in Grant's tomb in New York City?{
  =Grant
  #Yesss
  ~Ruben
  #Noooooo
}
```

Note that all the questions have the same wording, but different characteristics: some of them have a title, one of the have comments, two of them don't have a category... let's try it:

```
$ java -jar trivialparser-1.0.jar -i test.gift -o test.json
```

Now open the file test.json and you will see this:

```json
{
  "category" : "category",
  "title" : "title",
  "wording" : "Who is buried in Grant's tomb in New York City?",
  "options" : [ {
    "wording" : "Grant",
    "comment" : "Yesss",
    "correct" : true
  }, {
    "wording" : "Ruben",
    "comment" : "Noooooo",
    "correct" : false
  } ]
}
{
  "category" : "",
  "title" : "title",
  "wording" : "Who is buried in Grant's tomb in New York City?",
  "options" : [ {
    "wording" : "Grant",
    "comment" : "Yesss",
    "correct" : true
  }, {
    "wording" : "Ruben",
    "comment" : "Noooooo",
    "correct" : false
  } ]
}
{
  "category" : "",
  "title" : "",
  "wording" : "Who is buried in Grant's tomb in New York City?",
  "options" : [ {
    "wording" : "Grant",
    "comment" : "Yesss",
    "correct" : true
  }, {
    "wording" : "Ruben",
    "comment" : "Noooooo",
    "correct" : false
  } ]
}
{
  "category" : "category",
  "title" : "",
  "wording" : "Who is buried in Grant's tomb in New York City?",
  "options" : [ {
    "wording" : "Grant",
    "comment" : "Yesss",
    "correct" : true
  }, {
    "wording" : "Ruben",
    "comment" : "Noooooo",
    "correct" : false
  } ]
}
```

###Multiple choice questions

Multiple choice questions are those questions whose answers have an associated weight, which can be positive -if it is a correct answer-, negative -if is an incorrect answer- or 0.

Create a file named "test2.gift" with the following content:

```
//This is a multiple choice question
$CATEGORY:category
::title::What two people are entombed in Grants tomb?{
    ~%-100%No one
  ~%50%Grant
  ~%50%Grants wife
  ~%-100%Grants father
}

::title::What two people are entombed in Grants tomb?{
  ~%-100%No one
  ~%50%Grant
  ~%50%Grants wife
  ~%-100%Grants father
}
$CATEGORY:category
What two people are entombed in Grants tomb?{
  ~%-100%No one
  ~%50%Grant
  ~%50%Grants wife
  ~%-100%Grants father
}
What two people are entombed in Grants tomb?{
  ~%-100%No one
  ~%50%Grant
  ~%50%Grants wife
  ~%-100%Grants father
}
```

We execute the parser:

```
$ java -jar trivialparser-1.0.jar -i test2.gift -o test2.json
```

Let's see the contents of the test2.json file:

```
{
  "category" : "category",
  "title" : "title",
  "wording" : "What two people are entombed in Grants tomb?",
  "options" : [ {
    "wording" : "No one",
    "comment" : "",
    "weight" : -100
  }, {
    "wording" : "Grant",
    "comment" : "",
    "weight" : 50
  }, {
    "wording" : "Grants wife",
    "comment" : "",
    "weight" : 50
  }, {
    "wording" : "Grants father",
    "comment" : "",
    "weight" : -100
  } ]
}
{
  "category" : "",
  "title" : "title",
  "wording" : "What two people are entombed in Grants tomb?",
  "options" : [ {
    "wording" : "No one",
    "comment" : "",
    "weight" : -100
  }, {
    "wording" : "Grant",
    "comment" : "",
    "weight" : 50
  }, {
    "wording" : "Grants wife",
    "comment" : "",
    "weight" : 50
  }, {
    "wording" : "Grants father",
    "comment" : "",
    "weight" : -100
  } ]
}
{
  "category" : "category",
  "title" : "",
  "wording" : "What two people are entombed in Grants tomb?",
  "options" : [ {
    "wording" : "No one",
    "comment" : "",
    "weight" : -100
  }, {
    "wording" : "Grant",
    "comment" : "",
    "weight" : 50
  }, {
    "wording" : "Grants wife",
    "comment" : "",
    "weight" : 50
  }, {
    "wording" : "Grants father",
    "comment" : "",
    "weight" : -100
  } ]
}
{
  "category" : "",
  "title" : "",
  "wording" : "What two people are entombed in Grants tomb?",
  "options" : [ {
    "wording" : "No one",
    "comment" : "",
    "weight" : -100
  }, {
    "wording" : "Grant",
    "comment" : "",
    "weight" : 50
  }, {
    "wording" : "Grants wife",
    "comment" : "",
    "weight" : 50
  }, {
    "wording" : "Grants father",
    "comment" : "",
    "weight" : -100
  } ]
```

###True - false questions

True - false questions those questions whose answer can only be "true" or "false". Create a file test3.gift with the following content:

```
//This is a boolean question
$CATEGORY:category
::title::Grant was buried in a tomb in New York City.{T}

//Another question
::title::Grant was buried in a tomb in New York City.{T}

//Question with category but without title
$CATEGORY:category
Grant was buried in a tomb in New York City.{T}

//Question without category nor title
Grant was buried in a tomb in New York City.{T}
```
As in the previous example, we type the following:

```
$ java -jar trivialparser-1.0.jar --input test3.gift -o test3.json
```

The file test3.json should look like this:

```json
{
  "category" : "category",
  "title" : "title",
  "wording" : "Grant was buried in a tomb in New York City.",
  "answer" : {
    "comment" : "",
    "answer" : true
  }
}
{
  "category" : "",
  "title" : "title",
  "wording" : "Grant was buried in a tomb in New York City.",
  "answer" : {
    "comment" : "",
    "answer" : true
  }
}
{
  "category" : "category",
  "title" : "",
  "wording" : "Grant was buried in a tomb in New York City.",
  "answer" : {
    "comment" : "",
    "answer" : true
  }
}
{
  "category" : "",
  "title" : "",
  "wording" : "Grant was buried in a tomb in New York City.",
  "answer" : {
    "comment" : "",
    "answer" : true
  }
}
```

###Mixing different types of questions

Different types of questions can be written in the same file. Let's create a file questions.gift that has the three types of questions:

```
//comment
$CATEGORY:category
::title::Who is buried in Grant's tomb in New York City?{
=Grant
#Yesss
~Ruben
#Noooooo
}
//This is a multiple choice question
$CATEGORY:category
::title::What two people are entombed in Grants tomb?{
~%-100%No one
~%50%Grant
~%50%Grants wife
~%-100%Grants father
}
//This is a multiple choice question
$CATEGORY:category
::title::Grant was buried in a tomb in New York City.{T}
```

Now we pass the file as the input:

```
$ java -jar trivialparser-1.0.jar -i questions.gift -v
```

And the output will be:

```json
{
  "category" : "category",
  "title" : "title",
  "wording" : "Who is buried in Grant's tomb in New York City?",
  "options" : [ {
    "wording" : "Grant",
    "comment" : "Yesss",
    "correct" : true
  }, {
    "wording" : "Ruben",
    "comment" : "Noooooo",
    "correct" : false
  } ]
}
{
  "category" : "category",
  "title" : "title",
  "wording" : "What two people are entombed in Grants tomb?",
  "options" : [ {
    "wording" : "No one",
    "comment" : "",
    "weight" : -100
  }, {
    "wording" : "Grant",
    "comment" : "",
    "weight" : 50
  }, {
    "wording" : "Grants wife",
    "comment" : "",
    "weight" : 50
  }, {
    "wording" : "Grants father",
    "comment" : "",
    "weight" : -100
  } ]
}
{
  "category" : "category",
  "title" : "title",
  "wording" : "Grant was buried in a tomb in New York City.",
  "answer" : {
    "comment" : "",
    "answer" : true
  }
}

```

##QTI

We implemented the QTI (Question & Test Interoperability) format as explained here: http://www.imsglobal.org/question/qtiv2p1/imsqti_implv2p1.html

QTI format is written in XML. A fundamental difference is that we need at least two files: one file for a questions and another that points to the file where the question is.

### Single choice questions

Create a file "choice.qti" with the following contents:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!-- Thie example adapted from the PET Handbook, copyright University of Cambridge ESOL Examinations -->
<assessmentItem xmlns="http://www.imsglobal.org/xsd/imsqti_v2p1"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://www.imsglobal.org/xsd/imsqti_v2p1  http://www.imsglobal.org/xsd/qti/qtiv2p1/imsqti_v2p1.xsd"
  identifier="choice" title="Unattended Luggage" adaptive="false" timeDependent="false">
  <responseDeclaration identifier="RESPONSE" cardinality="single" baseType="identifier">
    <correctResponse>
      <value>ChoiceA</value>
    </correctResponse>
  </responseDeclaration>
  <outcomeDeclaration identifier="SCORE" cardinality="single" baseType="float">
    <defaultValue>
      <value>0</value>
    </defaultValue>
  </outcomeDeclaration>
  <itemBody>
    <p>Look at the text in the picture.</p>
    <p>
      <img src="images/sign.png" alt="NEVER LEAVE LUGGAGE UNATTENDED"/>
    </p>
    <choiceInteraction responseIdentifier="RESPONSE" shuffle="false" maxChoices="1">
      <prompt>What does it say?</prompt>
      <simpleChoice identifier="ChoiceA">You must stay with your luggage at all times.</simpleChoice>
      <simpleChoice identifier="ChoiceB">Do not let someone else look after your luggage.</simpleChoice>
      <simpleChoice identifier="ChoiceC">Remember your luggage when you leave.</simpleChoice>
    </choiceInteraction>
  </itemBody>
  <responseProcessing
    template="http://www.imsglobal.org/question/qti_v2p1/rptemplates/match_correct"/>
</assessmentItem>
```

### Multiple choice questions

Create a file "choice_multiple.qti" with the following contents:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!-- Thie example adapted from the PET Handbook, copyright University of Cambridge ESOL Examinations -->
<assessmentItem xmlns="http://www.imsglobal.org/xsd/imsqti_v2p1"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://www.imsglobal.org/xsd/imsqti_v2p1  http://www.imsglobal.org/xsd/qti/qtiv2p1/imsqti_v2p1.xsd"
  identifier="choice" title="Unattended Luggage" adaptive="false" timeDependent="false">
  <responseDeclaration identifier="RESPONSE" cardinality="single" baseType="identifier">
    <correctResponse>
      <value>ChoiceA</value>
    </correctResponse>
  </responseDeclaration>
  <outcomeDeclaration identifier="SCORE" cardinality="single" baseType="float">
    <defaultValue>
      <value>0</value>
    </defaultValue>
  </outcomeDeclaration>
  <itemBody>
    <p>Look at the text in the picture.</p>
    <p>
      <img src="images/sign.png" alt="NEVER LEAVE LUGGAGE UNATTENDED"/>
    </p>
    <choiceInteraction responseIdentifier="RESPONSE" shuffle="false" maxChoices="1">
      <prompt>What does it say?</prompt>
      <simpleChoice identifier="ChoiceA">You must stay with your luggage at all times.</simpleChoice>
      <simpleChoice identifier="ChoiceB">Do not let someone else look after your luggage.</simpleChoice>
      <simpleChoice identifier="ChoiceC">Remember your luggage when you leave.</simpleChoice>
    </choiceInteraction>
  </itemBody>
  <responseProcessing
    template="http://www.imsglobal.org/question/qti_v2p1/rptemplates/match_correct"/>
</assessmentItem>
```

### Putting it all together

Up to now we have created two files: choice.qti, which contains a single choice question, and choice_multiple.qti, which contains a multiple choice question. Now we need to create another file, assessment.qti, which represents the test that contains the two questions:

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<assessmentTest xmlns="http://www.imsglobal.org/xsd/imsqti_v2p1"
    xmlns:xi="http://www.w3.org/2001/XInclude" xmlns:m="http://www.w3.org/1998/Math/MathML"
    xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    toolVersion="0.1" toolName="Spectatus" title="Simple Feedback Test"
    identifier="SPECTATUS-GENERATED-TEST"
    xsi:schemaLocation="http://www.imsglobal.org/xsd/imsqti_v2p1 http://www.imsglobal.org/xsd/qti/qtiv2p1/imsqti_v2p1.xsd">
    <outcomeDeclaration baseType="identifier" cardinality="single" identifier="s1FB"/>
    <outcomeDeclaration baseType="identifier" cardinality="multiple" identifier="TEST_FEEDBACK"/>
    <testPart submissionMode="simultaneous" navigationMode="nonlinear" identifier="TP">
        <assessmentSection identifier="S1" visible="true" title="Section 1">
            <assessmentItemRef identifier="choice"
                href="choice.qti"/>
            <assessmentItemRef identifier="choice_multiple"
                href="choice_multiple.qti"/>
        </assessmentSection>
    </testPart>
</assessmentTest>

```

Note the the filenames of the questions are referenced in the "assessmentItemRef" field.
Let's run this example:

```
$ java -jar trivialparser-1.0.jar --input assessment.qti -o assessment.json
```

Let's see the contents of the generated assessment.json file:

```json
{
  "category" : "",
  "title" : "What does it say?",
  "wording" : "",
  "options" : [ {
    "wording" : "You must stay with your luggage at all times.",
    "comment" : "",
    "correct" : true
  }, {
    "wording" : "Do not let someone else look after your luggage.",
    "comment" : "",
    "correct" : false
  }, {
    "wording" : "Remember your luggage when you leave.",
    "comment" : "",
    "correct" : false
  } ]
}
{
  "category" : "",
  "title" : "Which of the following elements are used to form water?",
  "wording" : "",
  "options" : [ {
    "wording" : "Hydrogen",
    "comment" : "",
    "weight" : 50
  }, {
    "wording" : "Helium",
    "comment" : "",
    "weight" : -100
  }, {
    "wording" : "Carbon",
    "comment" : "",
    "weight" : -100
  }, {
    "wording" : "Oxygen",
    "comment" : "",
    "weight" : 50
  }, {
    "wording" : "Nitrogen",
    "comment" : "",
    "weight" : -100
  }, {
    "wording" : "Chlorine",
    "comment" : "",
    "weight" : -50
  } ]
```

## Inserting into MongoDB

The parser can also insert the output into a MongoDB. For this, it is necessary to have an instance of MongoDB running in your machine.

To tell the parser that it has to insert the questions in a database, you have to use the "insert" command.

You can optionally specify the name of the database and the collection; if not, the default database will be "trivial" and the default collection will be "questions".

Example:

```
java -jar trivialparser-1.0.jar insert -d mydb -c mycollection -i questions.gift
```

Now go to your mongoDB console:

```
> use mydb
switched to db mydb
> db.mycollection.find()
{ "_id" : ObjectId("55062ac29248f9f456f9fddc"), "category" : "category", "title" : "title", "wording" : "Who is buried in Grant's tomb in New York City?", "options" : [ { "wording" : "Grant", "comment" : "Yesss", "correct" : true }, { "wording" : "Ruben", "comment" : "Noooooo", "correct" : false } ] }
{ "_id" : ObjectId("55062ac49248f9f456f9fddd"), "category" : "category", "title" : "title", "wording" : "What two people are entombed in Grants tomb?", "options" : [ { "wording" : "No one", "comment" : "", "weight" : -100 }, { "wording" : "Grant", "comment" : "", "weight" : 50 }, { "wording" : "Grants wife", "comment" : "", "weight" : 50 }, { "wording" : "Grants father", "comment" : "", "weight" : -100 } ] }
{ "_id" : ObjectId("55062ac49248f9f456f9fdde"), "category" : "category", "title" : "title", "wording" : "Grant was buried in a tomb in New York City.", "answer" : { "comment" : "", "answer" : true } }
```

## Get the project 

First you will have to download Scala IDE for Eclipse: http://scala-ide.org/

You will also need to install the ScalaTest Plugin for Scala IDE: https://github.com/scalatest/scalatest-eclipse-plugin

If you have Git installed in your system, open a terminal and type:

```
git clone https://github.com/Arquisoft/Trivial_i2a
```

you can now open the project with Scala IDE









