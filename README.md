# cpaas_caller
This is a hot over-engineered pile of JVM code. It's main purpose
is to make calls with the help of Avaya CPaaS. These calls
point to different CPaaS projects. 

## Description
Basically it provides  the inverse function of Avaya's Cloud Zang 
applications. Instead of calling a phone number configured with an 
application (which points to one of our servers) you send
a request to this web server which makes the call to your number
and sends you to the right application. This project is meant
to stop any spam but still let users try out our CPaaS demos.

These are the spam problems faced:
* If you let users type out their number for your application
to call them you also let them enter numbers that do not belong
to them, possibly annoying innocent people.

* If you let users requests call without any authorization 
users can request calls over and over again and destroy
your credit card.

* If you give out a number for users to call you solve the first
problem, people can no longer make unsolicited calls, but 
your credit card is still under danger, since nothing stops
them from calling you over and over again. 

* If you give out a number for users to call, you will need
to have one phone number per application. So if you have
10 demos you will need 10 phone numbers.

This application solves them in the following manner:

* We do not give out a phone number to call. Our phone number
is not configured with any application either. This means any
incoming call is rejected.

* We let users request a call, but we only allow authorized
users to request said calls. We use this with an authorization
code that has a use limit.

The idea is that if you want to show our cool stuff to a soon to
be client you can give him a code for him to try out our demos.

In this way we: 

* Avoid spam. Only auth users can make calls. Needless to say
you should trust them and give them as little uses per code
as possible.

* Use one phone number for multiple apps since we dynamically
specify which server to interact with once the call is answered.

* Pay for the call. No one would call if we had to add little
letters that say *rates may apply*. Since we call we pay.

## Modules
As said, this is over-engineered. There are three modules,
`database`, `server` and `cli`.

### Database
Contains all hibernate entities, repositories and service layers
used by both the `cli` and `server` modules.

### Server
The server that receives requests to make calls. It authorizes
the request and, based on the endpoint hit, makes a call which
upon being answered will interact with one of our demos.

### CLI
Lets you manipulate the database trough a shell. Used to 
add/delete/edit authorization codes. It also lets you define
new endpoints and which demo they should use.

I chose a CLI over a web interface because:
* I am afraid of getting hacked
* I don't want to code a UI
* Spring Shell is cool
