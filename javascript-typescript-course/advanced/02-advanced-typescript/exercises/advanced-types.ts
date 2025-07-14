// Exercise: Create a mapped type that makes all properties of an object optional

type Optional<T> = {
  [P in keyof T]?: T[P];
};

// TODO: Use this mapped type with an interface