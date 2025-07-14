// Exercise: Fetch data from a public API and log the result

fetch('https://jsonplaceholder.typicode.com/todos/1')
  .then(response => response.json())
  .then(data => console.log(data));

// TODO: Rewrite the above using async/await