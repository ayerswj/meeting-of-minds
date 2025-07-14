// Exercise: Write a generic function that returns the first element of an array

function firstElement<T>(arr: T[]): T | undefined {
  return arr[0];
}

// TODO: Write a generic class that stores a value