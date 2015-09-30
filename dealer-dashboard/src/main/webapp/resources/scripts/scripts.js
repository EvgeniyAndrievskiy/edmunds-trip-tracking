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
	  map = new google.maps.Map(document.getElementById(tripMapContainerID), {
	    center: {lat: -34.397, lng: 150.644},
	    zoom: 8
	  });
}

function isEmpty(str) {
    return (!str || 0 === str.length);
}

function reloadDealerInventoryTable(dealerID, pathID) {
	$.get( "api/inventories?dealerId=" + dealerID + "&pathId=" + pathID, function( data ) {
		  $('#' + dealerInventoryTableID).bootstrapTable('load', data.inventories);
		  $('#' + dealerNameHeaderID).html(data.dealerName);
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