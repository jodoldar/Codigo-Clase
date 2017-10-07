importdata('./data/mini/trSep.dat',' ',4);
trSep = ans.data;
importdata('./data/mini/trSeplabels.dat',' ',4);
trSeplabels = ans.data;
importdata("./data/mini/tr.dat",' ',4);
tr = ans.data;
importdata("./data/mini/trlabels.dat",' ',4);
trlabels = ans.data;

axis([0 7 0 7]);
hold on
scatter(tr(trlabels==1,1),tr(trlabels==1,2),'x');
scatter(tr(trlabels==2,1),tr(trlabels==2,2),'s');
%hold off

res=svmtrain(trlabels,tr,'-t 0 -c 1000');

fprintf("Multiplicadores de Lagrange: \n");
disp(res.sv_coef);
fprintf("Vectores soporte: \n");
disp(full(res.SVs));

alfa = res.sv_coef;
clase = trlabels(res.sv_indices,1);
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
fprintf("Valores de la funcion lineal: \n\tx = %f\tb = %f\n",m,b);
svFun = @(x) (m*x + b);
fplot(svFun);
%plot(svFun(1:1:5));
b = -1*(sigma0-1)/sigma(2);
svFun = @(x) (m*x + b);
fplot(svFun);
%plot(svFun(1:1:5));
b = -1*(sigma0+1)/sigma(2);
svFun = @(x) (m*x + b);
fplot(svFun);
%plot(svFun(1:1:5));
hold off