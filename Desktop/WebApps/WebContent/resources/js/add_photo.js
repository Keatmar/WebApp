function addPhoto(){
	var num = parseInt(document.getElementById('number').value,10);
	var input = document.createElement("INPUT");
	input.setAttribute("class", "form-control");
	input.setAttribute("type", "file");
	input.setAttribute("name", "Photo"+num);
	num = isNaN(num) ? 0 : num;
	if(num % 2 == 0){
		document.getElementById("add_photo_left").appendChild(input);
	}else{
		document.getElementById("add_photo_right").appendChild(input);
	}
	num++;
	document.getElementById('number').value = num;
}

