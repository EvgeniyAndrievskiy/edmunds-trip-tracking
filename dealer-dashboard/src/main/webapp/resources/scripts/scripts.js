var tripMapContainerID = 'tripMapContainer';

var dealerIDInputID = 'dealerIDInput';

var dealerNameHeaderID = 'dealerNameHeader';

var dealerInventoryTableID = 'dealerInventoryTable';

var DEFAULT_DEALER_ID = 893;

var DEFAULT_PATH_ID = 1;

var tripsTableID = 'tripsTable';

$(function() {
    init();
});

function init(){
	refreshDealerInventoryTable($('#' + dealerIDInputID).val());
    $('#' + dealerInventoryTableID).bootstrapTable().on('post-body.bs.table', function (e, name, args) {
    	refreshDealerInventoryTable($('#' + dealerIDInputID).val());
    });
}

function initMap() {
	/*map = new google.maps.Map(document.getElementById(tripMapContainerID), {
		center: {lat: -34.397, lng: 150.644},
		zoom: 8
	});*/

	map = L.map(tripMapContainerID);
	map.setView([34.0522342, -118.2436849], 10);

	var tileLayer = L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
		attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
	}).addTo(map);

	var userId = 1;
	var tripId = 1;
	$.get("api/track?userId=" + userId + "&tripId=" + tripId, function(data) {
		L.polyline(data.points, {color: 'blue'}).addTo(map);
	});
}

function isEmpty(str) {
    return (!str || 0 === str.length);
}

function reloadDealerInventoryTable(dealerID, pathID) {
	$.get( "api/inventories?dealerId=" + dealerID + "&pathId=" + pathID, function( data ) {
		  $('#' + dealerInventoryTableID).bootstrapTable('load', data.inventories);
		  $('#' + dealerNameHeaderID).html(data.dealerName);
		  $('#speed-limit').text(data.averageSpeed);
		});
	refreshDealerInventoryTable(dealerID, pathID);
}

function refreshDealerInventoryTable(dealerID, pathID) {
	$('#' + dealerInventoryTableID + ' td:nth-child(1)').wrap(function() {
		var currentText = $(this).text();
		var result;
		if (isEmpty(currentText)) {
			result = '<td><td>';
		} else {
			result = '<td><div class="imageContainer"><img src="' + currentText + '"/></div></td>';
		}
		return result;
		});
}

function getSelectedTripID() {
	return ($('#' + tripsTableID).bootstrapTable('getSelections'))[0][2];
}