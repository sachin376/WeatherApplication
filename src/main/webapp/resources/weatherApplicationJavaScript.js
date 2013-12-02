$(document).ready(function() {
	$("#errorMsg").hide();
	$("#submitId").click(function() {
		$("#info").hide();
		$("#exception").hide();
		$("#errorMsg").hide();
		var isValidZipCode = isValidUSZip($("#zipCode").val());
		if (isValidZipCode) {
			$("#info").show();
			$("#exception").show();
		} else {
			$("#errorMsg").show();
		}
		return isValidZipCode;
	});
});

function isValidUSZip(sZip) {
	return /^\d{5}?$/.test(sZip);
}