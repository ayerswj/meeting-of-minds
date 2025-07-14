// Exercise: Write a function that uses a type guard to check if a value is a string

function isString(value: any): value is string {
  return typeof value === 'string';
}

// TODO: Use this type guard in another function