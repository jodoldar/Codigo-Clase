module NormalizeSpaces where
  normalize :: String -> String
  normalize = unwords . words