function nextGrade(select, grd) {
	var grade = parseInt(grd, 10);
	var nextGrade = grade + 1;
	if(grade == 1){
		$("#categories").val(select);
	}else{
		$("#categories").val($("#categories").val()+"->"+select);
	}
	$("#category_" + nextGrade).css("display", "");
	$("#category_" + nextGrade).find('option').remove().end();

	$.ajax({
		type : 'GET',
		data : {
			"parent" : select,
			"grade" : grade
		},
		url : 'searchCategory',
		success : function(result) {
			if (result != "[]") {
				result = result.replace("[", "");
				result = result.replace("]", "");
				var res = result.split(',');
				for (var i = 0; i < res.length; ++i) {
					$("#category_" + nextGrade).append(
							$("<option></option>").attr("id",
									"option" + nextGrade)
									.attr("role", "option").attr("value",
											res[i]).text(res[i]));

				}
			} else {
				$("#category_" + nextGrade).css("display", "none");
			}
		}
	});
}
