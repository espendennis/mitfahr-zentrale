

<script>
	$(function() {
		$(".alertdiv").addClass("alert-hidden");
		$("#form").submit(function(event) {
			if (!validateForm()) {
				event.preventDefault();
			}
		});

	})
	function validateForm() {
		var validates = true;
		var startingpoint = $("#startingpoint").val();
		var destination = $("#destination").val();
		var price = $("#price").val();
		if (startingpoint.length > 0 & startingpoint.length < 81) {
			$("#startingpointalert").addClass("alert-hidden");
		} else {
			$("#startingpointalert").removeClass("alert-hidden");
			validates = false;
		}
		
		if (destination.length > 0 && destination.length < 81) {
			$("#destinationalert").addClass("alert-hidden");
		} else {
			$("#destinationalert").removeClass("alert-hidden");
			validates = false;
		}
		return validates;
	}
</script>
