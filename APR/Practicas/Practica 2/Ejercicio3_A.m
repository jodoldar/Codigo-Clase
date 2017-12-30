nums = 1:10;
error = zeros(10,2);
for i=1:10
    [error(i,1),error(i,2)] = clasificationError('./data/spam/tr.dat','./data/spam/trlabels.dat','./data/spam/ts.dat','./data/spam/tslabels.dat',i);
    errorbar(nums,error(:,1),error(:,2));
end

xlabel("Numero de mixturas");
ylabel("Tasa de error");
errorbar(nums,error(:,1),error(:,2));
