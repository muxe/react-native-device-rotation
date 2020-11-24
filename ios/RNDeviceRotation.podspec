require 'json'
package = JSON.parse(File.read('../package.json'))

Pod::Spec.new do |s|
  s.name         = "RNDeviceRotation"
  
  s.version      = package["version"]
  s.license      = package["license"]
  s.summary      = package["description"]
  s.authors      = package["author"]
  s.homepage     = "https://github.com/muxe/react-native-device-rotation/"

  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/author/RNDeviceRotation.git", :tag => "master" }
  s.source_files  = "RNDeviceRotation/**/*.{h,m}"
  s.requires_arc = true


  s.dependency "React"
  #s.dependency "others"

end

  
