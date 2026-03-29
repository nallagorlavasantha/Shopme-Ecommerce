
function cancelUser(){
	window.location.href = usersRedirectLink;
}


//check email
	const userForm = document.getElementById("save-user-form");
	userForm.addEventListener("submit",async (e)=>{
		e.preventDefault();
		let email = e.target.email.value;
		console.log(location.href);
		const response = await fetch("/shopmeAdmin/users/new/checkEmail",{
			method:"POST",
			headers:{
				"content-type":"application/json"
			},
			body: JSON.stringify({
				email:email
			})
		});
		console.log(email);
		const data = await response.text();
		if(data == "ok"){
			userForm.submit();
		}else{
			window.alert("email is duplicated");
		}
		console.log(data);
	});
