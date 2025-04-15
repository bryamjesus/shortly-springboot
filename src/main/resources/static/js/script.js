const buttonShortUrl = document.querySelector('#buttonShortUrl');
const inputUrl = document.querySelector('#inputUrl');

buttonShortUrl.addEventListener('click', (e) => {
  let headers = new Headers();

  headers.append('Content-Type', 'application/json');
  headers.append('Accept', 'application/json');

  headers.append('Access-Control-Allow-Origin', 'http://localhost:8080');

  e.preventDefault();
  const url = inputUrl.value.trim();
  if (!url) {
    alert('Ingrese una URL');
    return;
  }

  const requestData = {
    url,
  };
  console.log(url);

  fetch('http://localhost:8080/shortly', {
    method: 'POST',
    mode: 'cors', // Indica que se trata de una solicitud CORS
    headers: {
      'Content-Type': 'application/json', // Especifica el tipo de contenido como JSON
    },
    body: JSON.stringify({
      // Aquí va el cuerpo de la solicitud en formato JSON
      url: 'https://ejemplo.com',
    }),
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error(`Error en la solicitud: ${response.status}`);
      }
      return response.json();
    })
    .then((data) => {
      console.log('Respuesta del servidor:', data);
    })
    .catch((error) => {
      console.error('Error durante la petición:', error);
    });
});
