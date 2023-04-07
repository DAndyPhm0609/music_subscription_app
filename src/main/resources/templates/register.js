const email = document.getElementById('email');
const password = document.getElementById('password');
const userName = document.getElementById('username');
const form = document.getElementById('registerForm');
const errorElement = document.getElementById('error')

form.addEventListener('submit', (e)=>{
    let messages = []
    if (email.value === '' || email.value == null){
        messages.push("email is required")
    }

    if(messages.length > 0){
    e.preventDefault()
    errorElement.innerTest = messages.join(', ')}
})
