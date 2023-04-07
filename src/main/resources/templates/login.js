var email = document.getElementById('email').value;
var password = document.getElementById('password').value;
const form = document.querySelector('form');
const errorElement = document.getElementById('error');
const button = document.getElementById('registerButton');

form.addEventListener('submit', (e)=>{
    let messages = []
    if(email === '' || email === null){
        messages.push("please insert email")
    }

    if(messages.length >0){
        e.preventDefault()
        errorElement.innerText = messages.join(', ')
    }
})
