$(async function() {
    await newUser();
});
async function newUser() {
    await fetch("/api/roles")
        .then(res => res.json())
        .then(roles => {
            roles.forEach(role => {
                let el = document.createElement("option");
                el.text = role.role;
                el.value = role.id;
                $('#newUserRoles')[0].appendChild(el);
            })
        })

    const form = document.forms["formNewUser"];

    form.addEventListener('submit', addNewUser)

    function addNewUser(e) {
        e.preventDefault();
        let newUserRoles = [];
        for (let i = 0; i < form.roles.options.length; i++) {
            if (form.roles.options[i].selected) newUserRoles.push({
                id : form.roles.options[i].value,
                name : form.roles.options[i].role
            })
        }
        fetch("/api/users", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: form.id.value,
                firstName: form.firstName.value,
                lastName: form.lastName.value,
                age: form.userAge.value,
                email: form.userEmail.value,
                password: form.password.value,
                roles: newUserRoles
            })
        }).then(() => {
            form.reset();
            allUsers();
            $('#allUsersTable').click();
        })
    }

}