// Exercise: Create a simple method decorator that logs when a method is called

function log(target: any, key: string, descriptor: PropertyDescriptor) {
  const original = descriptor.value;
  descriptor.value = function(...args: any[]) {
    console.log(`Calling ${key}`);
    return original.apply(this, args);
  };
}

class Example {
  @log
  greet() {
    console.log('Hello!');
  }
}

// TODO: Create another class and use the decorator