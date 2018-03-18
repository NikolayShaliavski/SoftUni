function parseXml(input) {
    var DOMParser = require('xmldom').DOMParser;
    var parser = new DOMParser();
    let xmlDoc = parser.parseFromString(input, "text/xml");

    var x = xmlDoc.getElementsByTagName("message")[0].childNodes[0].attributes;
    console.log(x);
    //console.log(xmlDoc.parentElement);
    // console.log(xmlDoc);
}

parseXml("<message from=\"John Doe\" to=\"Alice\">Not much, just chillin. How about you?</message>");