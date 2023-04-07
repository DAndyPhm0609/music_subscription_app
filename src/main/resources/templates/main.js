fetch("musics.json")
.then(function(response){
   return response.json();
})
.then(function(musics){
   let placeholder = document.querySelector("#music_table");
   let out = "";
   for(let music of musics){
      out += `
         <tr>
            <td>${music.title}</td>
            <td>${music.artist}</td>
            <td>${music.year}</td>
            <td>${music.web_url}</td>
            <td>${music.img_url}</td>
            <td>
         </tr>
      `;
   }
   placeholder.innerHTML = out;
});