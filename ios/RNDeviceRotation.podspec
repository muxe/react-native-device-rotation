
Pod::Spec.new do |s|
  s.name         = "RNDeviceRotation"
  s.version      = "1.0.0"
  s.summary      = "RNDeviceRotation"
  s.description  = <<-DESC
                  RNDeviceRotation
                   DESC
  s.homepage     = "https://github.com/muxe/react-native-device-rotation/"
  s.license      = "MIT"
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author             = { "author" => "author@domain.cn" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/author/RNDeviceRotation.git", :tag => "master" }
  s.source_files  = "**/*.{h,m}"
  s.requires_arc = true


  s.dependency "React"
  #s.dependency "others"

end

  
