
document.getElementById('result').textContent = 'Upload Image...';

document.querySelector('.fileContainer').addEventListener('click', function (e) {
    document.querySelector('.myFile').addEventListener('change', function (e) {
        document.getElementById('result').textContent = document.getElementById("myInput").files[0].name;
    })
});
