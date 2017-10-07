v1 = [1,3,8,9]; v2 = [-1,8,2,-3]; v = v1'*v2;
fprintf('Ejercicio 1: v1*v2 = %d\n\n',v1*v2');
fprintf('Ejercicio 2a: v1*v2\n');
disp(v);
fprintf('Ejercicio 2b: Determinante(v1*v2) = %f\n', det(v));
fprintf('Ejercicio 2c: Valores propios(v1*v2) =\n');
disp(eig(v));
v3 = v([1,3],[2,3]);
fprintf('Ejercicio 3a: Submatriz de v =\n');
disp(v3);
fprintf('Ejercicio 3b: Nueva submatriz de v =\n');
v3 = v3+ones(size(v3));
disp(v3);
fprintf('Ejercicio 3c: Determinante(v3) = %f\n', det(v3));
fprintf('Ejercicio 3d: Inversa(v3) = \n');
v4=inv(v3);
disp(v4);
num = max(max(v4));
[posX,posY] = find(v4==num);
fprintf('Ejercicio 4a: Máximo de v4 = %f, posición: %d,%d\n',num(1),posX,posY);
aux = v4>0;
[posX,posY] = find(aux);
fprintf('Ejercicio 4b:\n');
for i = 1:size(posX)
    fprintf('Punto %d: %d,%d\n',i,posX(i),posY(i));
end
fprintf('Ejercicio 4c:\n');
fprintf('Suma de las columnas: \n'); disp(sum(v4,1));
fprintf('Suma de las filas: \n'); disp(sum(v4,2));
save('Practica0.mat','v4','-ascii');

fprintf('Primera fila: \n'); disp(v4(1,:));
fprintf('Primera columna: \n'); disp(v4(:,1));

X = load('data');
X = X';
save('data_trans','X');