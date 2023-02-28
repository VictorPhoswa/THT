class Solution:
       def groupAnagrams(self, strs):
       
        strs.reverse()
        print(strs)
    
        result = {}

        for i in strs:

            x = "".join(sorted(i))

            if x in result:
                result[x].append(i)
               
            else:
                result[x] = [i]
        print(list(result.values()))
        return list(result.values())

ob1 = Solution()
print(ob1.groupAnagrams(["eat", "tea", "tan", "ate", "nat", "bat"]))


#The code does not work as it is supposed to
#Code is Understandable
#Indetation Error
#unident does not match any outer indentation
#The passed list(Strs) is not arranged orderly as required for desired output
#The sort function is not passed any argument(counter vairable i)
#The loop has complexity O(N) as we iterate through each string.
#Then, we sort each string in O(Nlog⁡N) time. using the in built function