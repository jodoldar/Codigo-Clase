importdata("./data/mini/tr.dat",' ',4);
tr = ans.data;
importdata("./data/mini/trlabels.dat",' ',4);
trlabels = ans.data;
C = 1000;
axis([0 7 0 7]);

hold on
scatter(tr(trlabels==1,1),tr(trlabels==1,2),'o');
scatter(tr(trlabels==2,1),tr(trlabels==2,2),'s');

input = sprintf('-t 0 -c %d',C);
res=svmtrain(trlabels,tr,input);

fprintf("Multiplicadores de Lagrange: \n");
disp(res.sv_coef);
fprintf("Vectores soporte: \n");
disp(full(res.SVs));

alfa = res.sv_coef;
%clase = trlabels(res.sv_indices,1);
equis = tr(res.sv_indices,:);
mult = alfa.*equis;
sigma = sum(mult);
sigma0  = clase(1)-sigma*equis(1,:)';

fprintf("Vector de pesos: ");
disp(sigma);
fprintf(" y umbral: ");
disp(sigma0);
fprintf("Margen: \n");
disp(1/norm(sigma));

m = -1*sigma(1)/sigma(2);
b = -1*sigma0/sigma(2);
fprintf("Valores de la funcion lineal: \n\tm = %f\tb = %f\n",m,b);
%Recta central
svFun = @(x) (m*x + b);
fplot(svFun);

%Recta inferior
b = -1*(sigma0-1)/sigma(2);
svFun = @(x) (m*x + b);
fplot(svFun);

%Recta superior
b = -1*(sigma0+1)/sigma(2);
svFun = @(x) (m*x + b);
fplot(svFun);
%hold off

alfaPrima = C - alfa;

equis2 = equis(alfaPrima>0,:);
alfa2 = alfa(alfaPrima>0,:);
val2 = (sigma*equis2'+sigma0);
val3 = abs(round(val2,2));
equisErr = equis2(val3~=1,:);
scatter(equisErr(:,1),equisErr(:,2),200,'x');

nAlfa = abs(alfa);
equis3 = equis(alfaPrima<=0,:);
alfa3 = alfa(alfaPrima<=0,:);
val0 = (sigma*equis3'+sigma0)-1;
val1 = ((-1*val1).*nAlfa')./nAlfa';
val2 = abs(round(val1,2));
equisErr = equis3(val2>1,:);
scatter(equisErr(:,1),equisErr(:,2),200,'x');
hold off

val2 = (sigma*equis2'+sigma0);
val1 = val0-1;
cedillas = ((-1*val1).*nAlfa')./nAlfa';