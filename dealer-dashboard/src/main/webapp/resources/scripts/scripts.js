var tripMapContainerID = 'tripMapContainer';

var dealerIDInputID = 'dealerIDInput';

var dealerInventoryTableID = 'dealerInventoryTable';

var DEFAULT_DEALER_ID = 90404;

$(function() {
    init();
});

function init(){
	$('#' + dealerIDInputID).val(DEFAULT_DEALER_ID); 
    refreshDealerInventoryTable(DEFAULT_DEALER_ID);
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

function refreshDealerInventoryTable(dealerZipCode) {
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