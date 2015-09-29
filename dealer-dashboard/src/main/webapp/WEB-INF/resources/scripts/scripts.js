var tripMapContainerID = 'tripMapContainer';

function init(){
	initMap();
}

$(function() {
    init();
});

function initMap() {
	  map = new google.maps.Map(document.getElementById(tripMapContainerID), {
	    center: {lat: -34.397, lng: 150.644},
	    zoom: 8
	  });
	}