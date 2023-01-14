// const modalForm = document.getElementById('modalForm');
async function deleteUser(id) {

    let data = await getUser(id);
    const { elements } = document.querySelector('#modalForm')

    const deleteTitle = document.getElementById('modal-title');
    deleteTitle.innerHTML = 'Delete user';
    const deleteButton = document.getElementById('modal-submit');
    deleteButton.innerHTML = 'Delete';
    modalForm.setAttribute('action', 'api/user/' + id);
    modalForm.addEventListener("submit", deleteData);

    let fields = modalForm.elements;
    for (let field of fields) {
        field.setAttribute('disabled', 'disabled');
    }


    for (const [ key, value ] of Object.entries(data) ) {
        const field = elements.namedItem(key);
        // if (key !== 'id') {
        //     field.setAttribute('disabled', 'disabled');
        // }
        // if (key !== 'password') {
        //     field && (field.value = value);
        // }
        field && (field.value = value);
        if (key === 'roles') {
            let options = Array.from(field);
            for (let role of value) {
                for (let option of options) {
                    if (option.value === role.name) {
                        option.selected = true;
                    }
                }
            }
        }
    }
}
async function getUser(id) {
    const url = 'api/user/' + id;
    let response = await fetch(url);
    return response.json();
}
async function deleteData(event) {
    event.preventDefault();

    const form = event.currentTarget;
    const url = form.action;

    // const formData = new FormData(form);
    // let object = {};
    // formData.forEach((value, key) => {
    //     if (key === 'id') {
    //         object[key] = value;
    //     }
    // });

    // let json = JSON.stringify(object);

    const fetchOptions = {
        method: "DELETE",
        // headers: {
        //     "Content-Type": "application/json",
        //     Accept: "application/json",
        // },
        // body: json,
    };

    await fetch(url, fetchOptions);

    form.reset();
    $('#close').click();
    await tableBuilder();
}