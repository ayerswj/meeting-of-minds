# Module 2: Advanced TypeScript

## Learning Objectives
- Use advanced types (conditional, mapped, utility types)
- Apply decorators
- Manipulate types with keyof, typeof, infer
- Configure TypeScript for large projects
- Test TypeScript code

---

## 1. Advanced Types
- Conditional types, mapped types, utility types

```ts
type IsString<T> = T extends string ? true : false;
type ReadonlyPerson = Readonly<{ name: string; age: number }>;
```

---

## 2. Decorators
- Experimental feature for annotating and modifying classes

```ts
function log(target: any, key: string) {
  console.log(`${key} was called`);
}

class Example {
  @log
  method() {}
}
```

---

## 3. Type Manipulation
- `keyof`, `typeof`, `infer`, template literal types

```ts
type Keys = keyof { a: number; b: string };
const obj = { x: 1 };
type ObjType = typeof obj;
```

---

## 4. Configuration & Tooling
- `tsconfig.json`, strict mode
- Linting and formatting

---

## 5. Testing TypeScript Code
- Using Jest and ts-jest

---

## 6. Exercises
- Create a mapped type
- Use a decorator
- Manipulate types with keyof and typeof
- Configure a TypeScript project
- Write a test for a TypeScript function

See the `exercises/` folder for starter files.

---
[Continue to Projects](../../projects/README.md)