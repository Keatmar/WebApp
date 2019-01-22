var map;
var myCenter = new google.maps.LatLng(38.680364303506494, 22.289061695337295);

function initialize() {
	var mapProp = {
		center : myCenter,
		zoom : 5,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};

	map = new google.maps.Map(document.getElementById("googleMap"), mapProp);

	var infowindow = new google.maps.InfoWindow();

	google.maps.event.addListener(map, 'click', function(event) {
		placeMarker(event.latLng);

	});
}

function placeMarker(location) {
	var marker = new google.maps.Marker({
		position : location,
		map : map,
	});
	var infowindow = new google.maps.InfoWindow({
		content : 'Latitude: ' + location.lat() + '<br>Longitude: '
				+ location.lng()
	});

	infowindow.open(map, marker);
	document.getElementById("Latitude").value = location.lat();
	document.getElementById("Longitude").value = location.lng();
	
}

google.maps.event.addDomListener(window, 'load', initialize);