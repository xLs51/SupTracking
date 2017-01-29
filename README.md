SupTracking
======

SupTracking is a web application made with Java EE.

It is a Proof of Concept of a cars GPS tracking system.

Functionalities
------------

- Registered user :
  - Add / Delete / Update a car to track
  - Add / Delete / Update a zone to disable alarm
  - Web Service to synchronize positions of users and cars
  - View and edit profile
  - View and edit invoices

- Administrator :
  - Show and manage all users
  - Show all invoices

Installation
------------

- Install Glassfish
- Setup a JDBC connection. The name of your JDBC Ressources should be the same as the `<jta-data-source></jta-data-source>` in [`persistence.xml`][1]
- Download this project
- Replace in [this file][3] your Google key `"https://www.google.com/maps/embed/v1/place?key=YOUR_GOOGLE_KEY";`
- Deploy the application

Once launched, you will find the database filled with [some data][2].

------------
###### SUPINFO Project - 4JVA - 04/2015

[1]: https://github.com/xLs51/SupTracking/blob/master/SupTrackingEJB/ejbModule/META-INF/persistence.xml
[2]: https://github.com/xLs51/SupTracking/blob/master/SupTrackingEJB/ejbModule/com/supinfo/suptracking/bean/InitServiceBean.java
[3]: https://github.com/xLs51/SupTracking/blob/master/SupTrackingWeb/src/com/supinfo/suptracking/beans/Index.java
