$('[id^="btn-delete"]').click(function() {
    let code = $(this).attr('data-code');
    let text = $(this).attr('data-text');

    $('#id').val(code);
    $('#text-delete').text(text);
});
$('[id^="btn-insert"]').click(function(){
    let id = $(this).attr('data-code');
    //let data = JSON.parse(id);

    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/Servlets_war/findById',
        data: {
            action: 'findById',
            id: id
        }
    }).done(function(res){
        console.log(res);
        let user = res.user;

        $('#lbl_name').text(user.idPerson.name);
        $('#lbl_lastname').text(user.idPerson.lastname);
        $('#lbl_email').text(user.email);
        $('#lbl_password').text(user.password);
    });
});

$('[id^="btn-details"]').click(function(){
    let id = $(this).attr('data-code');
    //let data = JSON.parse(id);

    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/Servlets_war/findById',
        data: {
            action: 'findById',
            id: id
        }
    }).done(function(res){
        console.log(res);
        let user = res.user;

        $('#lbl_name').text(user.idPerson.name);
        $('#lbl_lastname').text(user.idPerson.lastname);
        $('#lbl_email').text(user.email);
        $('#lbl_password').text(user.password);
    });
});
