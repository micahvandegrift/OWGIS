OWGIS
=====

For more info please visit [owgis.org](http://owgis.org)!

# What is OWGIS?
OWGIS is a Java web application that creates 
WebGIS sites by automatically writing HTML and JavaScript code. 

We built OWGIS because we want it an easy way to 
publish scientific maps on the web. OWGIS provides a
nice interface that can be easily customized and it 
can be used to publish any type of georeferenced data served
by an WMS server like [Geoserver](http://geoserver.org)
or by an [ncWMS](http://www.resc.rdg.ac.uk/trac/ncWMS/) sever. 

The default template of OWGIS can be tested 
<a href="http://owgis.servehttp.com:8080/OWGISTemplate/mapviewer"> here</a>,
and it will look similar to the following screenshot.
<img src="http://owgis.org/images/galery/DemoConfig.png"  title="OWGIS template">
<a href="https://travis-ci.org/driftyco/ionic"><img src="http://owgis.org/images/galery/DemoConfig.png" data-bindattr-164="164" title="Build Status Images"></a>

Each WebGIS site is configured by two types of files, a 
Java properties file stored at `web/WEB-INF/conf/MapViewConfig.properties`
and XML files located at `web/layers/TestLayers.xml`.
For a detailed information on the configuration files please
read the tutorials section at [owgis.org](http://owgis.org).

## Quick Start
Step 1. Clone the repository:
 
    git clone git@github.com:olmozavala/OWGIS.git

## Netbeans
Building a Netbeans project for OWGIS is very easy. 

Step 1. Clone the repository:

    git clone git@github.com:olmozavala/OWGIS.git

Step 2. Create new project in Netbeans

    File -> New Project (Next)
    Java Web -> Web Application with Existing Sources (Next)
    Point to the root folder of OWGIS and Change project name to 'OWGISTemplate' (Next)
    Select your servlet container  (Next)
    If not set by default set:
        Web Pages Folder -> web
        Web-INF Folder  -> WEB-INF
        Libraries Folder -> libraries 
        Source package folders -> src/java (Finish)

Step 3. Common Netbeans configuration (Project Properties)

    Sources -> Change source to JDK 7
    Run -> Relative URL -> /mapviewer (Ok):w

Once OWGIS template is working just modify the configuration
files for you convenience.

## Community 
## Authors

** Olmo Zavala**
+ <https://github.com/olmozavala>
+ <https://olmozavala.com>

** Arsalan Ahmed **
+ <https://github.com/arsa666>

## License
OWGIS is licensed under the MIT Open Source license. 
For more information, see the LICENSE file in this repository.
