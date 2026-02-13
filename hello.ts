type std = {
  name: string;
  age: number;
  isStd: boolean;
  marks: number[];
};

let shree: std = { name: "hardik", age: 20, isStd: true, marks: [1, 2, 3, 4] };

console.log(shree);

const addition = (num: number, num1: number): number => {
  return num + num1;
};

console.log(addition(5,4));