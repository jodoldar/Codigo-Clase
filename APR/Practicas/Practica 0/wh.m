  function [Tec] = wh(data)
    %número de muestras de aprendizaje y número de columnas
    [N,M]=size(data);
    DataX = [ones(N,1) data(:,1)];
    size(DataX);
    Tec = [0 0];
    Fac = 0.01;
    for j=1:100
        Vec = [0 0];
        for i=1:N
            Vec = Vec + (Tec*DataX(i,:)'-data(i,2))*[1 data(i,1)];
        end
        Tec = Tec-Fac*Vec;
    end
  end