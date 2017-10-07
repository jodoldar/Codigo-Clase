aux = importdata('data',' ',4);
data = aux.data;
datoWH = wh(data);
[N,M]=size(data);
SumX = sum(data(:,1));
SumY = sum(data(:,2));
SumXY = sum(data(:,1).*data(:,2));
MCm = (SumXY-(SumX*SumY)/N)/(sum(data(:,1).^2)-(SumX^2/N));
MCb = (SumY-MCm*SumX)/N;
test = linspace(0,1,N);
%Imprime los puntos
scatter(data(:,1),data(:,2))
hold on
%Imprime la recta de Widrow-Hoff
plot(test,test*datoWH(2)+datoWH(1),'g')
%Imprime la recta de minimos cuadrados
plot(test,test*MCm+MCb,'b')
hold off