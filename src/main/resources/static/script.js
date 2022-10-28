let emailInput;
let passwordInput;
let sendAuthenticationRequestBtn;


const prepareDOMElements = () => {
	emailInput = document.querySelector('.email_input');
	passwordInput = document.querySelector('.password_input');
	sendAuthenticationRequestBtn = document.querySelector('.send_authentication_request_btn');
}

const prepareDOMEvents = () => {
	sendAuthenticationRequestBtn.addEventListener('click', sendAuthenticationRequest)
}

function sendAuthenticationRequest(){
	console.log('request send');
	
	fetch('http://localhost:8080/authenticate', {
		method: 'POST',
		headers: {
			'Accept': 'application/json',
			'Content-type': 'application/json',
		},
		'Access-Control-Allow-Origin': '*',
		body: JSON.stringify({
			email: emailInput.value,
			password: passwordInput.value,
		}),
	})
	.then((response) => {console.log(response)})
	.catch((error) => console.log(error))
}


const main = () => {
	prepareDOMElements();
	prepareDOMEvents();
}

document.addEventListener('DOMContentLoaded', main);