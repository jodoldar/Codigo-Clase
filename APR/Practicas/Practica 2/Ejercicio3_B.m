%nums = 1:10;
%error = zeros(10,2);
for i=21:40
    nums = [nums i];
    [error(i,1),error(i,2)] = clasificationError('./data/usps/tr.dat','./data/usps/trlabels.dat','./data/usps/ts.dat','./data/usps/tslabels.dat',i);
    errorbar(nums,error(:,1),error(:,2));
end

xlabel("Numero de mixturas");
ylabel("Tasa de error");
errorbar(nums,error(:,1),error(:,2));
