</div>
</main>
<!--Main layout-->
<script
        type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/9.0.0/mdb.umd.min.js"
></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.min.js"
        integrity="sha384-VQqxDN0EQCkWoxt/0vsQvZswzTHUVOImccYmSyhJTp7kGtPed0Qcx8rK9h9YEgx+"
        crossorigin="anonymous"></script>
<script>
    function deleteCountry(id) {
        if (!id) {
            alert('ID is required!');// ID bo'lmasa, xato qaytaramiz
        }

        const url = `http://localhost:8080/country?id=` + id;

        // URL'ni konsolga chiqarish
        console.log('Deleting country with ID:', id);
        console.log('Fetch URL:', url);
        fetch(url, { method: 'DELETE' })
            .then(response => {
                if (response.ok) {
                    alert('Country deleted successfully!');
                } else {
                    response.text().then(msg => alert(msg)); // Xatolik xabarini olish
                }
            })
            .catch(error => alert('Error: ' + error));
    }
    function editCountry(id , name){

    }


</script>
</body>
</html>