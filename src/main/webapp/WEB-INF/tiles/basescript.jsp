	<script>
		$(function() {
			var path = window.location.pathname;
			var navbar = $(".navbar-nav");
			navbar.children("li").removeClass("active");
			if(path == "/"){
				navbar.find("#homeLink").addClass("active");
			}
			if(path == "/createoffer"){
				navbar.find("#createOfferLink").addClass("active");
			}
			if(path == "/about"){
				navbar.find("#aboutLink").addClass("active");
			}
			if(path == "/api"){
				navbar.find("#apiLink").addClass("active");
			}
			$("#btnProfil").click(function(){
				window.location.href="../profil/espendennis";
			})
});

	</script>