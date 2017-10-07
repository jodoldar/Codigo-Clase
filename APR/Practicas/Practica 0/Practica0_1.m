aux = importdata('data01',' ',4);
data = aux.data;
[fil,col] = size(data);
res = (data(:,1)+data(:,2)-ones(fil,1)*1.6)>0;
aciertos = res==data(:,3);
Pe = 1-sum(aciertos)/fil;
Ic = 1.96*sqrt((Pe*(1-Pe))/fil);
fprintf('Error de clasificacion = %f\n',Pe);
fprintf('Intervalo de confianza = [%f,%f]\n',Pe-Ic,Pe+Ic);