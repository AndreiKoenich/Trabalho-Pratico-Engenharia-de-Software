window.addEventListener("load", () => {
    function sendData() {
      const XHR = new XMLHttpRequest();
  
      // Bind the FormData object and the form element
      const FD = new FormData(form);
  
      // Define what happens on successful data submission
      XHR.addEventListener("load", (event) => {
        alert(event.target.responseText);
      });
  
      // Define what happens in case of error
      XHR.addEventListener("error", (event) => {
        alert('Oops! Something went wrong.');
      });
  
      // Set up our request
      XHR.open("POST", "http://localhost:8082/bicicleta");
      XHR.setRequestHeader("Content-type", "application/json");
      
      data='{\"marca\": \"';
      data=data+document.getElementById("bike_marca").value;
      data=data+'\",    \"modelo\" : \"';
      data=data+document.getElementById("bike_modelo").value;
      data=data+'\",    \"descricao\": \"';
      data=data+document.getElementById("bike_descricao").value;
      data=data+'\",    \"preco\": \"';
      data=data+document.getElementById("bike_preco").value;
      data=data+'\",    \"url_foto\": \"';
      data=data+document.getElementById("bike_url").value;
      data=data+'\",    \"peso\": \"';
      data=data+document.getElementById("bike_peso").value;
      data=data+'\",    \"acessorios\": \"';
      data=data+document.getElementById("bike_acessorios").value;
      data=data+'\",    \"desconto\": \"';
      data=data+document.getElementById("bike_desconto").value;
      data=data+'\"}';
      console.log(data);
      XHR.send(data);

    }
  
    // Get the form element
    const form = document.getElementById("newbike-form");
  
    // Add 'submit' event handler
    form.addEventListener("submit", (event) => {
      event.preventDefault();
  
      sendData();
    });
  });