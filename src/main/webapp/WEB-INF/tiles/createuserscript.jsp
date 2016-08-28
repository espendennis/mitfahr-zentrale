<script>
	$(function() {

		console.log("loaded");
		$(".alertdiv").addClass("alert-hidden");
		$("#password").keyup(checkPasswordsMatch);
		$("#confirmpassword").keyup(checkPasswordsMatch);
		$("#form").submit(function(event) {
			if(!validateForm()){
			event.preventDefault();								
			}
		});

	})
	
	function validateForm(){
		var validates = true;
		var username = $("#username").val();
		var firstname = $("#firstname").val();
		var lastname = $("#lastname").val();
		var email = $("#email").val();
		var phone = $("#phone").val();
		var password = $("#password").val();
		var confirmpass = $("#confirmpassword").val();
		var emailpattern = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		var phonepattern = /\(?([0-9]{3})\)?([ .-]?)([0-9]{3})\2([0-9]{4})/;
		
		if(username.length > 4 && username.length <81){
			$("#usernamealert").addClass("alert-hidden");
		} else {
			$("#usernamealert").removeClass("alert-hidden");
			validates = false;
		}
		if(firstname.length > 4 && firstname.length <81){
			$("#firstnamealert").addClass("alert-hidden");
		} else {
			$("#firstnamealert").removeClass("alert-hidden");
			validates = false;
		}
		if(lastname.length > 4 && lastname.length <81){
			$("#lastnamealert").addClass("alert-hidden");
		} else {
			$("#lastnamealert").removeClass("alert-hidden");
			validates = false;
		}
		if(phonepattern.test(phone)){
			$("#phonealert").addClass("alert-hidden");
		} else {
			$("#phonealert").removeClass("alert-hidden");
			validates = false;
		}
		if(emailpattern.test(email)){
			$("#emailalert").addClass("alert-hidden");
		} else {
			$("#emailalert").removeClass("alert-hidden");
			validates = false;
		}
		if (password.length >4 && password == confirmpass){
			console.log("passwort passt");
		} else {
			validates = false;
			console.log("passwort passt nicht");
		}
		console.log(validates);
		return validates;
		
	}

	function checkPasswordsMatch() {
		var password = $("#password").val();
		var confirmpass = $("#confirmpassword").val();

		if (password.length > 3 || confirmpass.length > 3) {
			if (password == confirmpass) {
				$("#passwordalert").addClass("alert-hidden");
			} else {
				$("#passwordalert").removeClass("alert-hidden");
			}
		}
	}

</script>