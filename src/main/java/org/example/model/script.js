function deleteBook(id){
    fetch(`http://localhost:8080/deleteBook/${id}`,{
        method:'DELETE'
    }).then(response =>{
        if (response.ok) {
            alert("Muvaffaqiyatli o'chirildi")
        }else{
            alert("Error")
        }
    }).catch(error => {
        console.error('Serverga ulanishda xatolik:', error);
    });
}