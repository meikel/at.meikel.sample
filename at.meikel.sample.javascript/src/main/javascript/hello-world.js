// http://code.google.com/p/js-test-driver/
// http://stackoverflow.com/questions/2070499/running-javascript-unit-tests-headlessly-in-a-continuous-integration-build

load('lib.js');

print('Hello world.');

print(add(1, -2.3e4));
function add(a, b) {
	return a+b;
}

print(add(3, 7));

print('fib(2) = ' + fib(2));