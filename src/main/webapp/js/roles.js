$(document).ready(function () {
    $('.del-btn').click(function (e) {
        const id = $(this).attr('id')
        const This = $(this)
        
        $.ajax({
            method: 'GET',
            url: `http://localhost:8080/api/roles/delete?id=${id}`,
            // data
        }) .done(function (data) {
            console.log(data)
            if(data.data) {
                console.log('Xoa Thanh Cong!')
                This.closest('tr').remove()
            } else {
                console.log('Xoa That Bai!')
            }
        })
    })
    
    $('#btn-add').click(function (e) {
        e.preventDefault()
        const name = $('#name').val()
        const desc = $('#desc').val()

        $.ajax({
            method: 'POST',
            url: 'http://localhost:8080/api/roles/add',
            data: {
                'name': name,
                'desc': desc
            }
        }) .done(function (data) {
            console.log(data)
            if(data.data) {
                console.log('Them Thanh Cong!')
            } else {
                console.log('Them That Bai!')
            }
        })
    })
})