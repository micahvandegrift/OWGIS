OWGIS
=====

For more info please visit [owgis.org](http://owgis.org)!

# What is OWGIS?
OWGIS is a Java web application that creates 
WebGIS sites by automatically writing HTML and JavaScript code. 

We built OWGIS because we wanted an easy way to 
publish scientific maps on the web. OWGIS provides a
self-contained WebGIS sites that can be easily customized.
OWGIS can publish any type of georeferenced data served
by an WMS server like [Geoserver](http://geoserver.org)
or by an [ncWMS](http://www.resc.rdg.ac.uk/trac/ncWMS/) sever. 
The later uses an extension of the WMS standard to 
serve 3D or 4D data stored as [NetCDF](http://www.unidata.ucar.edu/software/netcdf/) files.

The default template of OWGIS can be tested 
<a href="http://owgis.servehttp.com:8080/OWGISTemplate/mapviewer"> here</a>.

Each WebGIS site is configured by two types of files, a 
Java properties file stored at `web/WEB-INF/conf/MapViewConfig.properties`
and XML files located at `web/layers/TestLayers.xml`.
With these two files you can create new maps with many layers
and customize the look of your map. 

For a detailed information on the configuration files please
read the tutorials section at [owgis.org](http://owgis.org).

## Quick Start using ant
Step 1. Clone the repository:
 
    git clone git@github.com:olmozavala/OWGIS.git

Step 2. Edit `buildTemplate.xml` to match your paths, mainly the Tomcat folder.

Step 3. Build your war file:
    
    ant -f buildTemplate.xml dist

Step 3. Deploy the project in your servlet container, like [Tomcat](http://tomcat.apache.org/)

    cp OWGISTemplate.war /usr/local/tomcat/webapps

Step 4. Test your OWGIS Template normally at <http://localhost:8080/OWGISTemplate/mapviewer>

## Quick start using Netbeans
Building a Netbeans project for OWGIS is very easy. 

Step 1. Clone the repository:

    git clone git@github.com:olmozavala/OWGIS.git OWGISTemplate

Step 2. Create new project in Netbeans

    Delete `buildTemplate.xml` file
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
